package by.bsu.up.chat.logging.impl;

import by.bsu.up.chat.logging.Logger;

public class Log implements Logger {

    private static final String TEMPLATE = "[%s] %s";

    private String tag;

    private Log(Class<?> cls) {
        tag = String.format(TEMPLATE, cls.getName(), "%s");
    }
   /* public void write(String message){
        Timestamp time = new Timestamp(new Date().getTime());
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(src,true),"UTF-8")){
            writer.write(time.toString()+": "+String.format(tag,message));
            writer.write(System.lineSeparator());
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }*/
    @Override
    public void info(String message) {
        System.out.println(String.format(tag, message));
    }

    @Override
    public void error(String message, Throwable e) {
        System.err.println(String.format(tag, message));
        e.printStackTrace(System.err);
    }

    public static Log create(Class<?> cls) {
        return new Log(cls);
    }
}
