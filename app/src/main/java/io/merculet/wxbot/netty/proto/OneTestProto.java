package io.merculet.wxbot.netty.proto;

public final class OneTestProto {
    private static final com.google.protobuf.Descriptors.Descriptor
            internal_static_OneTest_descriptor;
    private static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internal_static_OneTest_fieldAccessorTable;
    private static com.google.protobuf.Descriptors.FileDescriptor
            descriptor;

    static {
        java.lang.String[] descriptorData = {
                "\n\rOneTest.proto\":\n\007OneTest\022\017\n\007test_id\030\001 " +
                        "\001(\005\022\r\n\005title\030\002 \001(\t\022\017\n\007content\030\003 \001(\tB*\n\032c" +
                        "om.example.user.nettydemoB\014OneTestProtob" +
                        "\006proto3"
        };
        com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
                new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
                    @Override
                    public com.google.protobuf.ExtensionRegistry assignDescriptors(
                            com.google.protobuf.Descriptors.FileDescriptor root) {
                        descriptor = root;
                        return null;
                    }
                };
        com.google.protobuf.Descriptors.FileDescriptor
                .internalBuildGeneratedFileFrom(descriptorData,
                        new com.google.protobuf.Descriptors.FileDescriptor[]{
                        }, assigner);
        internal_static_OneTest_descriptor =
                getDescriptor().getMessageTypes().get(0);
        internal_static_OneTest_fieldAccessorTable = new
                com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                internal_static_OneTest_descriptor,
                new java.lang.String[]{"TestId", "Title", "Content",});
    }

    private OneTestProto() {
    }

    public static void registerAllExtensions(
            com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(
            com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions(
                (com.google.protobuf.ExtensionRegistryLite) registry);
    }

    public static com.google.protobuf.Descriptors.FileDescriptor
    getDescriptor() {
        return descriptor;
    }

    public interface OneTestOrBuilder extends
            // @@protoc_insertion_point(interface_extends:OneTest)
            com.google.protobuf.MessageOrBuilder {

        /**
         * <code>int32 test_id = 1;</code>
         */
        int getTestId();

        /**
         * <code>string title = 2;</code>
         */
        java.lang.String getTitle();

        /**
         * <code>string title = 2;</code>
         */
        com.google.protobuf.ByteString
        getTitleBytes();

        /**
         * <code>string content = 3;</code>
         */
        java.lang.String getContent();

        /**
         * <code>string content = 3;</code>
         */
        com.google.protobuf.ByteString
        getContentBytes();
    }

    /**
     * Protobuf type {@code OneTest}
     */
    public static final class OneTest extends
            com.google.protobuf.GeneratedMessageV3 implements
            // @@protoc_insertion_point(message_implements:OneTest)
            OneTestOrBuilder {
        public static final int TEST_ID_FIELD_NUMBER = 1;
        public static final int TITLE_FIELD_NUMBER = 2;
        public static final int CONTENT_FIELD_NUMBER = 3;
        private static final long serialVersionUID = 0L;
        // @@protoc_insertion_point(class_scope:OneTest)
        private static final io.merculet.wxbot.netty.proto.OneTestProto.OneTest DEFAULT_INSTANCE;
        private static final com.google.protobuf.Parser<OneTest>
                PARSER = new com.google.protobuf.AbstractParser<OneTest>() {
            @java.lang.Override
            public OneTest parsePartialFrom(
                    com.google.protobuf.CodedInputStream input,
                    com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                    throws com.google.protobuf.InvalidProtocolBufferException {
                return new OneTest(input, extensionRegistry);
            }
        };

        static {
            DEFAULT_INSTANCE = new io.merculet.wxbot.netty.proto.OneTestProto.OneTest();
        }

        private int testId_;
        private volatile java.lang.Object title_;
        private volatile java.lang.Object content_;
        private byte memoizedIsInitialized = -1;
        // Use OneTest.newBuilder() to construct.
        private OneTest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
            super(builder);
        }

        private OneTest() {
            testId_ = 0;
            title_ = "";
            content_ = "";
        }

        private OneTest(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            this();
            if (extensionRegistry == null) {
                throw new java.lang.NullPointerException();
            }
            int mutable_bitField0_ = 0;
            com.google.protobuf.UnknownFieldSet.Builder unknownFields =
                    com.google.protobuf.UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0:
                            done = true;
                            break;
                        case 8: {

                            testId_ = input.readInt32();
                            break;
                        }
                        case 18: {
                            java.lang.String s = input.readStringRequireUtf8();

                            title_ = s;
                            break;
                        }
                        case 26: {
                            java.lang.String s = input.readStringRequireUtf8();

                            content_ = s;
                            break;
                        }
                        default: {
                            if (!parseUnknownFieldProto3(
                                    input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                            break;
                        }
                    }
                }
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (java.io.IOException e) {
                throw new com.google.protobuf.InvalidProtocolBufferException(
                        e).setUnfinishedMessage(this);
            } finally {
                this.unknownFields = unknownFields.build();
                makeExtensionsImmutable();
            }
        }

        public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
            return internal_static_OneTest_descriptor;
        }

        public static io.merculet.wxbot.netty.proto.OneTestProto.OneTest parseFrom(
                java.nio.ByteBuffer data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static io.merculet.wxbot.netty.proto.OneTestProto.OneTest parseFrom(
                java.nio.ByteBuffer data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static io.merculet.wxbot.netty.proto.OneTestProto.OneTest parseFrom(
                com.google.protobuf.ByteString data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static io.merculet.wxbot.netty.proto.OneTestProto.OneTest parseFrom(
                com.google.protobuf.ByteString data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static io.merculet.wxbot.netty.proto.OneTestProto.OneTest parseFrom(byte[] data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static io.merculet.wxbot.netty.proto.OneTestProto.OneTest parseFrom(
                byte[] data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static io.merculet.wxbot.netty.proto.OneTestProto.OneTest parseFrom(java.io.InputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input);
        }

        public static io.merculet.wxbot.netty.proto.OneTestProto.OneTest parseFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input, extensionRegistry);
        }

        public static io.merculet.wxbot.netty.proto.OneTestProto.OneTest parseDelimitedFrom(java.io.InputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseDelimitedWithIOException(PARSER, input);
        }

        public static io.merculet.wxbot.netty.proto.OneTestProto.OneTest parseDelimitedFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
        }

        public static io.merculet.wxbot.netty.proto.OneTestProto.OneTest parseFrom(
                com.google.protobuf.CodedInputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input);
        }

        public static io.merculet.wxbot.netty.proto.OneTestProto.OneTest parseFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(io.merculet.wxbot.netty.proto.OneTestProto.OneTest prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
        }

        public static io.merculet.wxbot.netty.proto.OneTestProto.OneTest getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static com.google.protobuf.Parser<OneTest> parser() {
            return PARSER;
        }

        @java.lang.Override
        public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
            return this.unknownFields;
        }

        @java.lang.Override
        protected FieldAccessorTable
        internalGetFieldAccessorTable() {
            return internal_static_OneTest_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            io.merculet.wxbot.netty.proto.OneTestProto.OneTest.class, io.merculet.wxbot.netty.proto.OneTestProto.OneTest.Builder.class);
        }

        /**
         * <code>int32 test_id = 1;</code>
         */
        @Override
        public int getTestId() {
            return testId_;
        }

        /**
         * <code>string title = 2;</code>
         */
        @Override
        public java.lang.String getTitle() {
            java.lang.Object ref = title_;
            if (ref instanceof java.lang.String) {
                return (java.lang.String) ref;
            } else {
                com.google.protobuf.ByteString bs =
                        (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                title_ = s;
                return s;
            }
        }

        /**
         * <code>string title = 2;</code>
         */
        @Override
        public com.google.protobuf.ByteString
        getTitleBytes() {
            java.lang.Object ref = title_;
            if (ref instanceof java.lang.String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8(
                                (java.lang.String) ref);
                title_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string content = 3;</code>
         */
        @Override
        public java.lang.String getContent() {
            java.lang.Object ref = content_;
            if (ref instanceof java.lang.String) {
                return (java.lang.String) ref;
            } else {
                com.google.protobuf.ByteString bs =
                        (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                content_ = s;
                return s;
            }
        }

        /**
         * <code>string content = 3;</code>
         */
        @Override
        public com.google.protobuf.ByteString
        getContentBytes() {
            java.lang.Object ref = content_;
            if (ref instanceof java.lang.String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8(
                                (java.lang.String) ref);
                content_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        @java.lang.Override
        public final boolean isInitialized() {
            byte isInitialized = memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }

            memoizedIsInitialized = 1;
            return true;
        }

        @java.lang.Override
        public void writeTo(com.google.protobuf.CodedOutputStream output)
                throws java.io.IOException {
            if (testId_ != 0) {
                output.writeInt32(1, testId_);
            }
            if (!getTitleBytes().isEmpty()) {
                com.google.protobuf.GeneratedMessageV3.writeString(output, 2, title_);
            }
            if (!getContentBytes().isEmpty()) {
                com.google.protobuf.GeneratedMessageV3.writeString(output, 3, content_);
            }
            unknownFields.writeTo(output);
        }

        @java.lang.Override
        public int getSerializedSize() {
            int size = memoizedSize;
            if (size != -1) {
                return size;
            }

            size = 0;
            if (testId_ != 0) {
                size += com.google.protobuf.CodedOutputStream
                        .computeInt32Size(1, testId_);
            }
            if (!getTitleBytes().isEmpty()) {
                size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, title_);
            }
            if (!getContentBytes().isEmpty()) {
                size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, content_);
            }
            size += unknownFields.getSerializedSize();
            memoizedSize = size;
            return size;
        }

        @java.lang.Override
        public boolean equals(final java.lang.Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof io.merculet.wxbot.netty.proto.OneTestProto.OneTest)) {
                return super.equals(obj);
            }
            io.merculet.wxbot.netty.proto.OneTestProto.OneTest other = (io.merculet.wxbot.netty.proto.OneTestProto.OneTest) obj;

            boolean result = true;
            result = result && (getTestId()
                    == other.getTestId());
            result = result && getTitle()
                    .equals(other.getTitle());
            result = result && getContent()
                    .equals(other.getContent());
            result = result && unknownFields.equals(other.unknownFields);
            return result;
        }

        @java.lang.Override
        public int hashCode() {
            if (memoizedHashCode != 0) {
                return memoizedHashCode;
            }
            int hash = 41;
            hash = (19 * hash) + getDescriptor().hashCode();
            hash = (37 * hash) + TEST_ID_FIELD_NUMBER;
            hash = (53 * hash) + getTestId();
            hash = (37 * hash) + TITLE_FIELD_NUMBER;
            hash = (53 * hash) + getTitle().hashCode();
            hash = (37 * hash) + CONTENT_FIELD_NUMBER;
            hash = (53 * hash) + getContent().hashCode();
            hash = (29 * hash) + unknownFields.hashCode();
            memoizedHashCode = hash;
            return hash;
        }

        @java.lang.Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        @java.lang.Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE
                    ? new Builder() : new Builder().mergeFrom(this);
        }

        @java.lang.Override
        protected Builder newBuilderForType(
                BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        @java.lang.Override
        public com.google.protobuf.Parser<OneTest> getParserForType() {
            return PARSER;
        }

        @java.lang.Override
        public io.merculet.wxbot.netty.proto.OneTestProto.OneTest getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        /**
         * Protobuf type {@code OneTest}
         */
        public static final class Builder extends
                com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
                // @@protoc_insertion_point(builder_implements:OneTest)
                io.merculet.wxbot.netty.proto.OneTestProto.OneTestOrBuilder {
            private int testId_;
            private java.lang.Object title_ = "";
            private java.lang.Object content_ = "";

            // Construct using io.merculet.wxbot.netty.proto.OneTestProto.OneTest.newBuilder()
            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(
                    BuilderParent parent) {
                super(parent);
                maybeForceBuilderInitialization();
            }

            public static final com.google.protobuf.Descriptors.Descriptor
            getDescriptor() {
                return internal_static_OneTest_descriptor;
            }

            @java.lang.Override
            protected FieldAccessorTable
            internalGetFieldAccessorTable() {
                return internal_static_OneTest_fieldAccessorTable
                        .ensureFieldAccessorsInitialized(
                                io.merculet.wxbot.netty.proto.OneTestProto.OneTest.class, io.merculet.wxbot.netty.proto.OneTestProto.OneTest.Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                if (com.google.protobuf.GeneratedMessageV3
                        .alwaysUseFieldBuilders) {
                }
            }

            @java.lang.Override
            public Builder clear() {
                super.clear();
                testId_ = 0;

                title_ = "";

                content_ = "";

                return this;
            }

            @java.lang.Override
            public com.google.protobuf.Descriptors.Descriptor
            getDescriptorForType() {
                return internal_static_OneTest_descriptor;
            }

            @java.lang.Override
            public io.merculet.wxbot.netty.proto.OneTestProto.OneTest getDefaultInstanceForType() {
                return getDefaultInstance();
            }

            @java.lang.Override
            public io.merculet.wxbot.netty.proto.OneTestProto.OneTest build() {
                io.merculet.wxbot.netty.proto.OneTestProto.OneTest result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            @java.lang.Override
            public io.merculet.wxbot.netty.proto.OneTestProto.OneTest buildPartial() {
                io.merculet.wxbot.netty.proto.OneTestProto.OneTest result = new io.merculet.wxbot.netty.proto.OneTestProto.OneTest(this);
                result.testId_ = testId_;
                result.title_ = title_;
                result.content_ = content_;
                onBuilt();
                return result;
            }

            @java.lang.Override
            public Builder clone() {
                return (Builder) super.clone();
            }

            @java.lang.Override
            public Builder setField(
                    com.google.protobuf.Descriptors.FieldDescriptor field,
                    java.lang.Object value) {
                return (Builder) super.setField(field, value);
            }

            @java.lang.Override
            public Builder clearField(
                    com.google.protobuf.Descriptors.FieldDescriptor field) {
                return (Builder) super.clearField(field);
            }

            @java.lang.Override
            public Builder clearOneof(
                    com.google.protobuf.Descriptors.OneofDescriptor oneof) {
                return (Builder) super.clearOneof(oneof);
            }

            @java.lang.Override
            public Builder setRepeatedField(
                    com.google.protobuf.Descriptors.FieldDescriptor field,
                    int index, java.lang.Object value) {
                return (Builder) super.setRepeatedField(field, index, value);
            }

            @java.lang.Override
            public Builder addRepeatedField(
                    com.google.protobuf.Descriptors.FieldDescriptor field,
                    java.lang.Object value) {
                return (Builder) super.addRepeatedField(field, value);
            }

            @java.lang.Override
            public Builder mergeFrom(com.google.protobuf.Message other) {
                if (other instanceof io.merculet.wxbot.netty.proto.OneTestProto.OneTest) {
                    return mergeFrom((io.merculet.wxbot.netty.proto.OneTestProto.OneTest) other);
                } else {
                    super.mergeFrom(other);
                    return this;
                }
            }

            public Builder mergeFrom(io.merculet.wxbot.netty.proto.OneTestProto.OneTest other) {
                if (other == getDefaultInstance()) {
                    return this;
                }
                if (other.getTestId() != 0) {
                    setTestId(other.getTestId());
                }
                if (!other.getTitle().isEmpty()) {
                    title_ = other.title_;
                    onChanged();
                }
                if (!other.getContent().isEmpty()) {
                    content_ = other.content_;
                    onChanged();
                }
                this.mergeUnknownFields(other.unknownFields);
                onChanged();
                return this;
            }

            @java.lang.Override
            public final boolean isInitialized() {
                return true;
            }

            @java.lang.Override
            public Builder mergeFrom(
                    com.google.protobuf.CodedInputStream input,
                    com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                    throws java.io.IOException {
                io.merculet.wxbot.netty.proto.OneTestProto.OneTest parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                    parsedMessage = (io.merculet.wxbot.netty.proto.OneTestProto.OneTest) e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
                return this;
            }

            /**
             * <code>int32 test_id = 1;</code>
             */
            @Override
            public int getTestId() {
                return testId_;
            }

            /**
             * <code>int32 test_id = 1;</code>
             */
            public Builder setTestId(int value) {

                testId_ = value;
                onChanged();
                return this;
            }

            /**
             * <code>int32 test_id = 1;</code>
             */
            public Builder clearTestId() {

                testId_ = 0;
                onChanged();
                return this;
            }

            /**
             * <code>string title = 2;</code>
             */
            @Override
            public java.lang.String getTitle() {
                java.lang.Object ref = title_;
                if (!(ref instanceof java.lang.String)) {
                    com.google.protobuf.ByteString bs =
                            (com.google.protobuf.ByteString) ref;
                    java.lang.String s = bs.toStringUtf8();
                    title_ = s;
                    return s;
                } else {
                    return (java.lang.String) ref;
                }
            }

            /**
             * <code>string title = 2;</code>
             */
            public Builder setTitle(
                    java.lang.String value) {
                if (value == null) {
                    throw new NullPointerException();
                }

                title_ = value;
                onChanged();
                return this;
            }

            /**
             * <code>string title = 2;</code>
             */
            @Override
            public com.google.protobuf.ByteString
            getTitleBytes() {
                java.lang.Object ref = title_;
                if (ref instanceof String) {
                    com.google.protobuf.ByteString b =
                            com.google.protobuf.ByteString.copyFromUtf8(
                                    (java.lang.String) ref);
                    title_ = b;
                    return b;
                } else {
                    return (com.google.protobuf.ByteString) ref;
                }
            }

            /**
             * <code>string title = 2;</code>
             */
            public Builder setTitleBytes(
                    com.google.protobuf.ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                checkByteStringIsUtf8(value);

                title_ = value;
                onChanged();
                return this;
            }

            /**
             * <code>string title = 2;</code>
             */
            public Builder clearTitle() {

                title_ = getDefaultInstance().getTitle();
                onChanged();
                return this;
            }

            /**
             * <code>string content = 3;</code>
             */
            @Override
            public java.lang.String getContent() {
                java.lang.Object ref = content_;
                if (!(ref instanceof java.lang.String)) {
                    com.google.protobuf.ByteString bs =
                            (com.google.protobuf.ByteString) ref;
                    java.lang.String s = bs.toStringUtf8();
                    content_ = s;
                    return s;
                } else {
                    return (java.lang.String) ref;
                }
            }

            /**
             * <code>string content = 3;</code>
             */
            public Builder setContent(
                    java.lang.String value) {
                if (value == null) {
                    throw new NullPointerException();
                }

                content_ = value;
                onChanged();
                return this;
            }

            /**
             * <code>string content = 3;</code>
             */
            public com.google.protobuf.ByteString
            getContentBytes() {
                java.lang.Object ref = content_;
                if (ref instanceof String) {
                    com.google.protobuf.ByteString b =
                            com.google.protobuf.ByteString.copyFromUtf8(
                                    (java.lang.String) ref);
                    content_ = b;
                    return b;
                } else {
                    return (com.google.protobuf.ByteString) ref;
                }
            }

            /**
             * <code>string content = 3;</code>
             */
            public Builder setContentBytes(
                    com.google.protobuf.ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                checkByteStringIsUtf8(value);

                content_ = value;
                onChanged();
                return this;
            }

            /**
             * <code>string content = 3;</code>
             */
            public Builder clearContent() {

                content_ = getDefaultInstance().getContent();
                onChanged();
                return this;
            }

            @java.lang.Override
            public final Builder setUnknownFields(
                    final com.google.protobuf.UnknownFieldSet unknownFields) {
                return super.setUnknownFieldsProto3(unknownFields);
            }

            @java.lang.Override
            public final Builder mergeUnknownFields(
                    final com.google.protobuf.UnknownFieldSet unknownFields) {
                return super.mergeUnknownFields(unknownFields);
            }


            // @@protoc_insertion_point(builder_scope:OneTest)
        }

    }

    // @@protoc_insertion_point(outer_class_scope)
}
