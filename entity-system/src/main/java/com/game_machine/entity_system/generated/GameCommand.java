
package com.game_machine.entity_system.generated;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.List;

import com.dyuproject.protostuff.GraphIOUtil;
import com.dyuproject.protostuff.Input;
import com.dyuproject.protostuff.Message;
import com.dyuproject.protostuff.Output;

import java.io.ByteArrayOutputStream;
import com.dyuproject.protostuff.JsonIOUtil;
import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtobufIOUtil;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.game_machine.entity_system.Entity;
import com.game_machine.entity_system.Entities;
import com.game_machine.entity_system.Component;

import com.dyuproject.protostuff.Pipe;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.UninitializedMessageException;

public final class GameCommand extends com.game_machine.entity_system.Component implements Externalizable, Message<GameCommand>, Schema<GameCommand>
{

    public static Schema<GameCommand> getSchema()
    {
        return DEFAULT_INSTANCE;
    }

    public static GameCommand getDefaultInstance()
    {
        return DEFAULT_INSTANCE;
    }

    static final GameCommand DEFAULT_INSTANCE = new GameCommand();

    private String name;
    private Integer entityId;
    private TestObject testObject;
    

    public GameCommand()
    {
        
    }

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getEntityId() {
		return entityId;
	}
	public void setEntityId(Integer entityId) {
		this.entityId = entityId;
	}
	public TestObject getTestObject() {
		return testObject;
	}
	public void setTestObject(TestObject testObject) {
		this.testObject = testObject;
	}

  
    // java serialization

    public void readExternal(ObjectInput in) throws IOException
    {
        GraphIOUtil.mergeDelimitedFrom(in, this, this);
    }

    public void writeExternal(ObjectOutput out) throws IOException
    {
        GraphIOUtil.writeDelimitedTo(out, this, this);
    }

    // message method

    public Schema<GameCommand> cachedSchema()
    {
        return DEFAULT_INSTANCE;
    }

    // schema methods

    public GameCommand newMessage()
    {
        return new GameCommand();
    }

    public Class<GameCommand> typeClass()
    {
        return GameCommand.class;
    }

    public String messageName()
    {
        return GameCommand.class.getSimpleName();
    }

    public String messageFullName()
    {
        return GameCommand.class.getName();
    }

    public boolean isInitialized(GameCommand message)
    {
        return true;
    }

    public void mergeFrom(Input input, GameCommand message) throws IOException
    {
        for(int number = input.readFieldNumber(this);; number = input.readFieldNumber(this))
        {
            switch(number)
            {
                case 0:
                    return;
            	case 1:
                	message.name = input.readString();
                	break;
                	
            	case 2:
                	message.entityId = input.readInt32();
                	break;
                	
            	case 3:
                	message.testObject = input.mergeObject(message.testObject, TestObject.getSchema());
                    break;
                	
            
                default:
                    input.handleUnknownField(number, this);
            }   
        }
    }


    public void writeTo(Output output, GameCommand message) throws IOException
    {
    	
    	if(message.name == null)
            throw new UninitializedMessageException(message);

    	
    	if(message.name != null)
            output.writeString(1, message.name, false);
    	
    	

    	
    	if(message.entityId != null)
            output.writeInt32(2, message.entityId, false);
    	
    	

    	
    	if(message.testObject != null)
    		output.writeObject(3, message.testObject, TestObject.getSchema(), false);
    	
    	
    }

    public String getFieldName(int number)
    {
        switch(number)
        {
        	case 1: return "name";
        	case 2: return "entityId";
        	case 3: return "testObject";
            default: return null;
        }
    }

    public int getFieldNumber(String name)
    {
        final Integer number = __fieldMap.get(name);
        return number == null ? 0 : number.intValue();
    }

    private static final java.util.HashMap<String,Integer> __fieldMap = new java.util.HashMap<String,Integer>();
    static
    {
    	__fieldMap.put("name", 1);
    	__fieldMap.put("entityId", 2);
    	__fieldMap.put("testObject", 3);
    }
   
   public static List<String> getFields() {
	ArrayList<String> fieldNames = new ArrayList<String>();
	String fieldName = null;
	Integer i = 1;
	
    while(true) { 
		fieldName = GameCommand.getSchema().getFieldName(i);
		if (fieldName == null) {
			break;
		}
		fieldNames.add(fieldName);
		i++;
	}
	return fieldNames;
}

public static GameCommand parseFrom(byte[] bytes) {
	GameCommand message = new GameCommand();
	ProtobufIOUtil.mergeFrom(bytes, message, RuntimeSchema.getSchema(GameCommand.class));
	return message;
}
	
public byte[] toByteArray(String encoding) {
	if (encoding.equals("protobuf")) {
		return toProtobuf();
	} else if (encoding.equals("json")) {
		return toJson();
	} else {
		throw new RuntimeException("No encoding specified");
	}
}

public byte[] toJson() {
	boolean numeric = false;
	ByteArrayOutputStream out = new ByteArrayOutputStream();
	try {
		JsonIOUtil.writeTo(out, this, GameCommand.getSchema(), numeric);
	} catch (IOException e) {
		e.printStackTrace();
		throw new RuntimeException("Json encoding failed");
	}
	return out.toByteArray();
}
		
public byte[] toProtobuf() {
	LinkedBuffer buffer = LinkedBuffer.allocate(1024);
	byte[] bytes = null;

	try {
		bytes = ProtobufIOUtil.toByteArray(this, RuntimeSchema.getSchema(GameCommand.class), buffer);
		buffer.clear();
	} catch (Exception e) {
		e.printStackTrace();
		throw new RuntimeException("Protobuf encoding failed");
	}
	return bytes;
}

 
    

}
