
package io.gamemachine.client.messages;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.HashMap;
import java.io.UnsupportedEncodingException;

import io.protostuff.ByteString;
import io.protostuff.GraphIOUtil;
import io.protostuff.Input;
import io.protostuff.Message;
import io.protostuff.Output;
import io.protostuff.ProtobufOutput;

import java.io.ByteArrayOutputStream;
import io.protostuff.JsonIOUtil;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.runtime.RuntimeSchema;

import io.gamemachine.util.LocalLinkedBuffer;


import java.nio.charset.Charset;


import io.protostuff.Schema;
import io.protostuff.UninitializedMessageException;


@SuppressWarnings("unused")
public final class Quaternion implements Externalizable, Message<Quaternion>, Schema<Quaternion>{



    public static Schema<Quaternion> getSchema()
    {
        return DEFAULT_INSTANCE;
    }

    public static Quaternion getDefaultInstance()
    {
        return DEFAULT_INSTANCE;
    }

    static final Quaternion DEFAULT_INSTANCE = new Quaternion();

    			public Float w;
	    
        			public Float x;
	    
        			public Float y;
	    
        			public Float z;
	    
        			public Integer wi;
	    
        			public Integer xi;
	    
        			public Integer yi;
	    
        			public Integer zi;
	    
      
    public Quaternion()
    {
        
    }


	

	    
    public Boolean hasW()  {
        return w == null ? false : true;
    }
        
		public Float getW() {
		return w;
	}
	
	public Quaternion setW(Float w) {
		this.w = w;
		return this;	}
	
		    
    public Boolean hasX()  {
        return x == null ? false : true;
    }
        
		public Float getX() {
		return x;
	}
	
	public Quaternion setX(Float x) {
		this.x = x;
		return this;	}
	
		    
    public Boolean hasY()  {
        return y == null ? false : true;
    }
        
		public Float getY() {
		return y;
	}
	
	public Quaternion setY(Float y) {
		this.y = y;
		return this;	}
	
		    
    public Boolean hasZ()  {
        return z == null ? false : true;
    }
        
		public Float getZ() {
		return z;
	}
	
	public Quaternion setZ(Float z) {
		this.z = z;
		return this;	}
	
		    
    public Boolean hasWi()  {
        return wi == null ? false : true;
    }
        
		public Integer getWi() {
		return wi;
	}
	
	public Quaternion setWi(Integer wi) {
		this.wi = wi;
		return this;	}
	
		    
    public Boolean hasXi()  {
        return xi == null ? false : true;
    }
        
		public Integer getXi() {
		return xi;
	}
	
	public Quaternion setXi(Integer xi) {
		this.xi = xi;
		return this;	}
	
		    
    public Boolean hasYi()  {
        return yi == null ? false : true;
    }
        
		public Integer getYi() {
		return yi;
	}
	
	public Quaternion setYi(Integer yi) {
		this.yi = yi;
		return this;	}
	
		    
    public Boolean hasZi()  {
        return zi == null ? false : true;
    }
        
		public Integer getZi() {
		return zi;
	}
	
	public Quaternion setZi(Integer zi) {
		this.zi = zi;
		return this;	}
	
	
  
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

    public Schema<Quaternion> cachedSchema()
    {
        return DEFAULT_INSTANCE;
    }

    // schema methods

    public Quaternion newMessage()
    {
        return new Quaternion();
    }

    public Class<Quaternion> typeClass()
    {
        return Quaternion.class;
    }

    public String messageName()
    {
        return Quaternion.class.getSimpleName();
    }

    public String messageFullName()
    {
        return Quaternion.class.getName();
    }

    public boolean isInitialized(Quaternion message)
    {
        return true;
    }

    public void mergeFrom(Input input, Quaternion message) throws IOException
    {
        for(int number = input.readFieldNumber(this);; number = input.readFieldNumber(this))
        {
            switch(number)
            {
                case 0:
                    return;
                            	case 1:
            	                	                	message.w = input.readFloat();
                	break;
                	                	
                            	            	case 2:
            	                	                	message.x = input.readFloat();
                	break;
                	                	
                            	            	case 3:
            	                	                	message.y = input.readFloat();
                	break;
                	                	
                            	            	case 4:
            	                	                	message.z = input.readFloat();
                	break;
                	                	
                            	            	case 5:
            	                	                	message.wi = input.readInt32();
                	break;
                	                	
                            	            	case 6:
            	                	                	message.xi = input.readInt32();
                	break;
                	                	
                            	            	case 7:
            	                	                	message.yi = input.readInt32();
                	break;
                	                	
                            	            	case 8:
            	                	                	message.zi = input.readInt32();
                	break;
                	                	
                            	            
                default:
                    input.handleUnknownField(number, this);
            }   
        }
    }


    public void writeTo(Output output, Quaternion message) throws IOException
    {
    	    	
    	    	
    	    	    	if(message.w != null)
            output.writeFloat(1, message.w, false);
    	    	
    	            	
    	    	
    	    	    	if(message.x != null)
            output.writeFloat(2, message.x, false);
    	    	
    	            	
    	    	
    	    	    	if(message.y != null)
            output.writeFloat(3, message.y, false);
    	    	
    	            	
    	    	
    	    	    	if(message.z != null)
            output.writeFloat(4, message.z, false);
    	    	
    	            	
    	    	
    	    	    	if(message.wi != null)
            output.writeInt32(5, message.wi, false);
    	    	
    	            	
    	    	
    	    	    	if(message.xi != null)
            output.writeInt32(6, message.xi, false);
    	    	
    	            	
    	    	
    	    	    	if(message.yi != null)
            output.writeInt32(7, message.yi, false);
    	    	
    	            	
    	    	
    	    	    	if(message.zi != null)
            output.writeInt32(8, message.zi, false);
    	    	
    	            	
    }

    public String getFieldName(int number)
    {
        switch(number)
        {
        	        	case 1: return "w";
        	        	case 2: return "x";
        	        	case 3: return "y";
        	        	case 4: return "z";
        	        	case 5: return "wi";
        	        	case 6: return "xi";
        	        	case 7: return "yi";
        	        	case 8: return "zi";
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
    	    	__fieldMap.put("w", 1);
    	    	__fieldMap.put("x", 2);
    	    	__fieldMap.put("y", 3);
    	    	__fieldMap.put("z", 4);
    	    	__fieldMap.put("wi", 5);
    	    	__fieldMap.put("xi", 6);
    	    	__fieldMap.put("yi", 7);
    	    	__fieldMap.put("zi", 8);
    	    }
   
   public static List<String> getFields() {
	ArrayList<String> fieldNames = new ArrayList<String>();
	String fieldName = null;
	Integer i = 1;
	
    while(true) { 
		fieldName = Quaternion.getSchema().getFieldName(i);
		if (fieldName == null) {
			break;
		}
		fieldNames.add(fieldName);
		i++;
	}
	return fieldNames;
}

public static Quaternion parseFrom(byte[] bytes) {
	Quaternion message = new Quaternion();
	ProtobufIOUtil.mergeFrom(bytes, message, Quaternion.getSchema());
	return message;
}

public static Quaternion parseFromJson(String json) throws IOException {
	byte[] bytes = json.getBytes(Charset.forName("UTF-8"));
	Quaternion message = new Quaternion();
	JsonIOUtil.mergeFrom(bytes, message, Quaternion.getSchema(), false);
	return message;
}

public Quaternion clone() {
	byte[] bytes = this.toByteArray();
	Quaternion quaternion = Quaternion.parseFrom(bytes);
	return quaternion;
}
	
public byte[] toByteArray() {
	return toProtobuf();
	//return toJson();
}

public String toJson() {
	boolean numeric = false;
	ByteArrayOutputStream out = new ByteArrayOutputStream();
	try {
		JsonIOUtil.writeTo(out, this, Quaternion.getSchema(), numeric);
	} catch (IOException e) {
		e.printStackTrace();
		throw new RuntimeException("Json encoding failed");
	}
	String json = new String(out.toByteArray(), Charset.forName("UTF-8"));
	return json;
}

public byte[] toPrefixedByteArray() {
	LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
  Schema<Quaternion> schema = Quaternion.getSchema();
    
	final ByteArrayOutputStream out = new ByteArrayOutputStream();
    final ProtobufOutput output = new ProtobufOutput(buffer);
    try
    {
    	schema.writeTo(output, this);
        final int size = output.getSize();
        ProtobufOutput.writeRawVarInt32Bytes(out, size);
        final int msgSize = LinkedBuffer.writeTo(out, buffer);
        assert size == msgSize;
        
        buffer.clear();
        return out.toByteArray();
    }
    catch (IOException e)
    {
        throw new RuntimeException("Serializing to a byte array threw an IOException " + 
                "(should never happen).", e);
    }
 
}

public byte[] toProtobuf() {
	LinkedBuffer buffer = LocalLinkedBuffer.get();
	byte[] bytes = null;

	try {
		bytes = ProtobufIOUtil.toByteArray(this, Quaternion.getSchema(), buffer);
		buffer.clear();
	} catch (Exception e) {
		buffer.clear();
		e.printStackTrace();
		throw new RuntimeException("Protobuf encoding failed");
	}
	return bytes;
}

}
