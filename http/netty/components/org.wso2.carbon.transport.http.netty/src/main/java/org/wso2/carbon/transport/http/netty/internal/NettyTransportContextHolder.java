/*
 *  Copyright (c) 2015 WSO2 Inc. (http://wso2.com) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 *
 */
package org.wso2.carbon.transport.http.netty.internal;

import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wso2.carbon.messaging.CarbonMessageProcessor;
import org.wso2.carbon.messaging.TransportListenerManager;
import org.wso2.carbon.messaging.handler.HandlerExecutor;
import org.wso2.carbon.transport.http.netty.config.ListenerConfiguration;

import java.util.HashMap;
import java.util.Map;

/**
 * DataHolder for the Netty transport.
 */
public class NettyTransportContextHolder {
    private static final Logger log = LoggerFactory.getLogger(NettyTransportContextHolder.class);

    private static NettyTransportContextHolder instance = new NettyTransportContextHolder();
    private BundleContext bundleContext;
    private CarbonMessageProcessor messageProcessor;
    private HandlerExecutor handlerExecutor;
    private Map<String, ListenerConfiguration> listenerConfigurations = new HashMap<>();
    private TransportListenerManager manager;

    public ListenerConfiguration getListenerConfiguration(String id) {
        return listenerConfigurations.get(id);
    }

    public void setListenerConfiguration(String id, ListenerConfiguration config) {
        listenerConfigurations.put(id, config);
    }

    private NettyTransportContextHolder() {

    }

    public static NettyTransportContextHolder getInstance() {
        return instance;
    }


    public void setBundleContext(BundleContext bundleContext) {
        this.bundleContext = bundleContext;
    }

    public BundleContext getBundleContext() {
        return this.bundleContext;
    }

    public CarbonMessageProcessor getMessageProcessor() {
        return messageProcessor;
    }

    public void addMessageProcessor(CarbonMessageProcessor carbonMessageProcessor) {
        this.messageProcessor = carbonMessageProcessor;
    }

    public void removeMessageProcessor(CarbonMessageProcessor carbonMessageProcessor) {
        if (carbonMessageProcessor.getId().equals(messageProcessor.getId())) {
            messageProcessor = null;
        }
    }

    public TransportListenerManager getManager() {
        return manager;
    }

    public void removeManager() {
        manager = null;
    }

    public void setManager(TransportListenerManager manager) {
        this.manager = manager;
    }

    public void setHandlerExecutor(HandlerExecutor handlerExecutor) {
        this.handlerExecutor = handlerExecutor;
    }

    public HandlerExecutor getHandlerExecutor() {
        return handlerExecutor;

    }
}
