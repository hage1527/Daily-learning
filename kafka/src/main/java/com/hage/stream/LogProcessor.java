package com.hage.stream;

import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorContext;

public class LogProcessor implements Processor<byte[],byte[]> {
    private ProcessorContext context;
    @Override
    public void init(ProcessorContext processorContext) {
        this.context = processorContext;
    }

    @Override
    public void process(byte[] key, byte[] value) {
        String input = new String(value);
        if (input.contains(">>>")){
             input = input.split(">>>")[1].trim();
             context.forward(key, input.getBytes());
        }else
            context.forward(key, input.getBytes());

    }

    @Override
    public void punctuate(long l) {

    }

    @Override
    public void close() {

    }
}
