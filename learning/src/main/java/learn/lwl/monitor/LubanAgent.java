package learn.lwl.monitor;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

public class LubanAgent {
    public static void premain(String arg, Instrumentation inst) {

        System.out.println("premain" + arg);
        //所有的class在装载之前都会调用这个方法
        inst.addTransformer(new ClassFileTransformer() {
            @Override
            public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
                if (!"learn.lwl.monitor.LubanImpl".equals(className)) {
                    return null;
                }
                //java assist 改造字节码
//                ClassPool pool=new ClassPool();
                return new byte[0];
            }
        });
    }
}
