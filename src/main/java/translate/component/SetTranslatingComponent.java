package translate.component;

import translate.ClassDiagramConfig;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public abstract class SetTranslatingComponent<T> implements TranslatingComponent {
    protected final Set<T> set;
    protected final Class<T> type;

    protected SetTranslatingComponent(Class<T> type) {
        this.type = type;
        this.set = new HashSet<>();
    }

    // needs custom implementation for class & interface
    public void safeAdd(Object object) {
        if (type.isInstance(object)) {
            set.add(type.cast(object));
        }
    }

    public void add(T... elements) {
        set.addAll(Arrays.asList(elements));
    }

    public void add(Collection<? extends T> collection) {
        set.addAll(collection);
    }

    public Class<T> type() {
        return type;
    }

    @Override
    public void write(StringBuilder builder) {
        for (var element : set) {
            writeComponent(element, builder);
        }
    }

    public abstract void writeComponent(T element, StringBuilder builder);
}
