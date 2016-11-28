/*
 * Copyright (c) 2016 Kevin Herron
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 *   http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 *   http://www.eclipse.org/org/documents/edl-v10.html.
 */

package org.eclipse.milo.opcua.stack.core.types.structured;

import javax.annotation.Nullable;

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessage;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("PublishRequest")
public class PublishRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.PublishRequest;
    public static final NodeId BinaryEncodingId = Identifiers.PublishRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.PublishRequest_Encoding_DefaultXml;

    protected final RequestHeader _requestHeader;
    protected final SubscriptionAcknowledgement[] _subscriptionAcknowledgements;

    public PublishRequest() {
        this._requestHeader = null;
        this._subscriptionAcknowledgements = null;
    }

    public PublishRequest(RequestHeader _requestHeader, SubscriptionAcknowledgement[] _subscriptionAcknowledgements) {
        this._requestHeader = _requestHeader;
        this._subscriptionAcknowledgements = _subscriptionAcknowledgements;
    }

    public RequestHeader getRequestHeader() {
        return _requestHeader;
    }

    @Nullable
    public SubscriptionAcknowledgement[] getSubscriptionAcknowledgements() {
        return _subscriptionAcknowledgements;
    }

    @Override
    public NodeId getTypeId() {
        return TypeId;
    }

    @Override
    public NodeId getBinaryEncodingId() {
        return BinaryEncodingId;
    }

    @Override
    public NodeId getXmlEncodingId() {
        return XmlEncodingId;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("RequestHeader", _requestHeader)
            .add("SubscriptionAcknowledgements", _subscriptionAcknowledgements)
            .toString();
    }

    public static void encode(PublishRequest publishRequest, UaEncoder encoder) {
        encoder.encodeSerializable("RequestHeader", publishRequest._requestHeader != null ? publishRequest._requestHeader : new RequestHeader());
        encoder.encodeArray("SubscriptionAcknowledgements", publishRequest._subscriptionAcknowledgements, encoder::encodeSerializable);
    }

    public static PublishRequest decode(UaDecoder decoder) {
        RequestHeader _requestHeader = decoder.decodeSerializable("RequestHeader", RequestHeader.class);
        SubscriptionAcknowledgement[] _subscriptionAcknowledgements = decoder.decodeArray("SubscriptionAcknowledgements", decoder::decodeSerializable, SubscriptionAcknowledgement.class);

        return new PublishRequest(_requestHeader, _subscriptionAcknowledgements);
    }

}
