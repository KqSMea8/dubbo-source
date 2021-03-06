package com.alibaba.dubbo.rpc.protocol.thrift;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.remoting.Channel;
import com.alibaba.dubbo.remoting.Codec2;
import com.alibaba.dubbo.remoting.buffer.ChannelBuffer;
import com.alibaba.dubbo.remoting.buffer.ChannelBufferOutputStream;
import com.alibaba.dubbo.remoting.exchange.Request;
import com.alibaba.dubbo.remoting.exchange.Response;
import com.alibaba.dubbo.rpc.Invocation;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.*;
import org.apache.thrift.transport.TIOStreamTransport;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author <a href="mailto:gang.lvg@alibaba-inc.com">kimi</a>
 */
public class ThriftNativeCodec implements Codec2 {

    private final AtomicInteger thriftSeq = new AtomicInteger(0);

    protected static TProtocol newProtocol(URL url, ChannelBuffer buffer) throws IOException {
        String protocol = url.getParameter(ThriftConstants.THRIFT_PROTOCOL_KEY,
                ThriftConstants.DEFAULT_PROTOCOL);
        if (ThriftConstants.BINARY_THRIFT_PROTOCOL.equals(protocol)) {
            return new TBinaryProtocol(new TIOStreamTransport(new ChannelBufferOutputStream(buffer)));
        }
        throw new IOException("Unsupported protocol type " + protocol);
    }

    public void encode(Channel channel, ChannelBuffer buffer, Object message)
            throws IOException {
        if (message instanceof Request) {
            encodeRequest(channel, buffer, (Request) message);
        } else if (message instanceof Response) {
            encodeResponse(channel, buffer, (Response) message);
        } else {
            throw new IOException("Unsupported message type "
                    + message.getClass().getName());
        }
    }

    protected void encodeRequest(Channel channel, ChannelBuffer buffer, Request request)
            throws IOException {
        Invocation invocation = (Invocation) request.getData();
        TProtocol protocol = newProtocol(channel.getUrl(), buffer);
        try {
            protocol.writeMessageBegin(new TMessage(
                    invocation.getMethodName(), TMessageType.CALL,
                    thriftSeq.getAndIncrement()));
            protocol.writeStructBegin(new TStruct(invocation.getMethodName() + "_args"));
            for (int i = 0; i < invocation.getParameterTypes().length; i++) {
                Class<?> type = invocation.getParameterTypes()[i];

            }
        } catch (TException e) {
            throw new IOException(e.getMessage(), e);
        }

    }

    protected void encodeResponse(Channel channel, ChannelBuffer buffer, Response response)
            throws IOException {

    }

    public Object decode(Channel channel, ChannelBuffer buffer) throws IOException {
        return null;
    }

}
