package learn.lwl.jvm;

public class MyClassLoader extends ClassLoader {
    private String name;
    private String path;
    private String suffix = ".class";

    public MyClassLoader(String name) {
        super();
        this.name = name;
    }

    public MyClassLoader(String name, ClassLoader parent) {
        super(parent);
        this.name = name;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }

    private byte[] loadData() {
        String classPath = name.replaceAll(".", "\\");
        return null;
    }
}
