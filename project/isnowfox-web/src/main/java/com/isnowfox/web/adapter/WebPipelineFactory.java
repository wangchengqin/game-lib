package com.isnowfox.web.adapter;

import com.isnowfox.web.Config;
import com.isnowfox.web.Server;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.handler.codec.http.HttpRequestDecoder;
import org.jboss.netty.handler.codec.http.HttpResponseEncoder;

import static org.jboss.netty.channel.Channels.pipeline;


public class WebPipelineFactory implements ChannelPipelineFactory {
    private Server server;
    private Config config;

    public WebPipelineFactory(Server server, Config config) {
        this.server = server;
        this.config = config;
    }

    public ChannelPipeline getPipeline() throws Exception {
        // Create a default pipeline implementation.
        ChannelPipeline pipeline = pipeline();

        // Uncomment the following line if you want HTTPS
        // SSLEngine engine =
        // SecureChatSslContextFactory.getServerContext().createSSLEngine();
        // engine.setUseClientMode(false);
        // pipeline.addLast("ssl", new SslHandler(engine));
        pipeline.addLast("decoder", new HttpRequestDecoder());
        // Uncomment the following line if you don't want to handle HttpChunks.
        // pipeline.addLast("aggregator", new HttpChunkAggregator(1048576));
        pipeline.addLast("encoder", new HttpResponseEncoder());
        // Remove the following line if you don't want automatic content
        // compression.
        //	pipeline.addLast("deflater", new HttpContentCompressor());
        pipeline.addLast("handler", new WebAdapter(config, server.getDispatcher()));
        return pipeline;
    }
}
