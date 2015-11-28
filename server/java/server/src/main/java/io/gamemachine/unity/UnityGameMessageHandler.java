package io.gamemachine.unity;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.UntypedActor;
import akka.pattern.AskableActorSelection;
import akka.util.Timeout;
import io.gamemachine.core.ActorUtil;
import io.gamemachine.messages.ClientMessage;
import io.gamemachine.messages.GameMessage;
import io.gamemachine.messages.UnityGameMessage;
import io.gamemachine.net.Connection;
import io.gamemachine.net.udp.NettyUdpServerHandler;
import io.gamemachine.routing.RoundRobin;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

public class UnityGameMessageHandler extends UntypedActor {

	private static Map<Long, ActorRef> pending = new ConcurrentHashMap<Long, ActorRef>();
	private static AtomicLong messageId = new AtomicLong();
	private static Timeout t = new Timeout(Duration.create(100, TimeUnit.MILLISECONDS));
	private static Map<String, ArrayList<String>> unityActors = new ConcurrentHashMap<String, ArrayList<String>>();
	private static Map<String, RoundRobin<String>> roundRobins = new ConcurrentHashMap<String, RoundRobin<String>>();

	public static String name = UnityGameMessageHandler.class.getSimpleName();
	private static final Logger logger = LoggerFactory.getLogger(UnityGameMessageHandler.class);

	private static UnityGameMessage ask(UnityGameMessage message) {
		ActorSelection sel = ActorUtil.getSelectionByName(name);
		AskableActorSelection askable = new AskableActorSelection(sel);
		Future<Object> future = askable.ask(message, t);
		try {
			Object result = Await.result(future, t.duration());
			return (UnityGameMessage) result;
		} catch (Exception e) {
			return null;
		}
	}

	public static void tell(GameMessage gameMessage, String actorName) {
		UnityGameMessage unityGameMessage = createUnityGameMessage(gameMessage, actorName,
				UnityGameMessage.MessageType.Tell);
		if (unityGameMessage == null) {
			return;
		}

		ActorSelection sel = ActorUtil.getSelectionByName(name);
		sel.tell(unityGameMessage, null);
	}

	public static GameMessage ask(GameMessage gameMessage, String actorName) {
		UnityGameMessage unityGameMessage = createUnityGameMessage(gameMessage, actorName,
				UnityGameMessage.MessageType.Ask);
		if (unityGameMessage == null) {
			return null;
		}

		UnityGameMessage response = ask(unityGameMessage);
		if (response == null) {
			return null;
		} else {
			return response.gameMessage;
		}
	}

	public static boolean hasActor(String actorName) {
		if (unityActors.containsKey(actorName)) {
			return true;
		} else {
			return false;
		}
	}

	private static void ensureActorList(String actorName) {
		if (!hasActor(actorName)) {
			unityActors.put(actorName, new ArrayList<String>());
		}
	}

	private static synchronized void removeActor(String actorName, String playerId) {
		ensureActorList(actorName);

		unityActors.get(actorName).remove(playerId);
		
		RoundRobin<String> roundRobin = RoundRobin.create(unityActors.get(actorName));
		roundRobins.put(actorName, roundRobin);
		
		logger.warn("removing unity actor " + actorName + " player id " + playerId);
	}

	private static synchronized void addActor(String actorName, String playerId) {
		ensureActorList(actorName);

		unityActors.get(actorName).add(playerId);

		RoundRobin<String> roundRobin = RoundRobin.create(unityActors.get(actorName));
		roundRobins.put(actorName, roundRobin);

		logger.warn("adding unity actor " + actorName + " player id " + playerId);
	}
	
	private static String playerIdForActor(String actorName) {
		if (roundRobins.containsKey(actorName)) {
			return roundRobins.get(actorName).getOne();
		} else {
			return null;
		}
	}

	private static UnityGameMessage createUnityGameMessage(GameMessage gameMessage, String actorName,
			UnityGameMessage.MessageType messageType) {
		if (!hasActor(actorName)) {
			logger.warn("unity actor not found " + actorName);
			return null;
		}

		UnityGameMessage unityGameMessage = new UnityGameMessage();
		unityGameMessage.messageType = messageType;
		unityGameMessage.actorName = actorName;
		unityGameMessage.gameMessage = gameMessage;

		String playerId = playerIdForActor(actorName);
		if (Strings.isNullOrEmpty(playerId)) {
			return null;
		}
		
		unityGameMessage.playerId = playerId;

		if (!Connection.hasConnection(playerId)) {
			logger.warn("No connection found for playerId " + playerId);
			removeActor(actorName, playerId);
			return null;
		} else {
			return unityGameMessage;
		}
	}

	@Override
	public void onReceive(Object message) throws Exception {
		UnityGameMessage unityGameMessage = (UnityGameMessage) message;

		if (unityGameMessage.messageType == UnityGameMessage.MessageType.Register) {
			addActor(unityGameMessage.actorName, unityGameMessage.playerId);
			logger.warn("Registered unity actor " + unityGameMessage.actorName + " for playerId "
					+ unityGameMessage.playerId);
			return;
		}

		if (unityGameMessage.messageId > 0) {
			if (unityGameMessage.messageType == UnityGameMessage.MessageType.Ask) {
				if (pending.containsKey(unityGameMessage.messageId)) {
					ActorRef ref = pending.get(unityGameMessage.messageId);
					ref.tell(unityGameMessage, getSelf());
					pending.remove(unityGameMessage.messageId);
				} else {
					logger.warn("message id not found " + unityGameMessage.messageId);
				}
			} else {
				logger.warn("Unity responded to tell " + unityGameMessage.messageId);
			}

		} else {

			ClientMessage clientMessage = new ClientMessage();
			clientMessage.unityGameMessage = unityGameMessage;

			Connection connection = Connection.getConnection(unityGameMessage.playerId);
			if (connection != null) {
				if (unityGameMessage.messageType == UnityGameMessage.MessageType.Ask) {
					unityGameMessage.messageId = messageId.getAndIncrement();
					pending.put(unityGameMessage.messageId, getSender());
				}

				NettyUdpServerHandler.sendMessage(connection.clientId, clientMessage.toByteArray());
			} else {
				logger.warn("No connection found for playerId " + unityGameMessage.playerId);
				removeActor(unityGameMessage.actorName,unityGameMessage.playerId);
			}
		}
	}
}