package TwitterSearch.Util;

public interface ActionHandler<T> {

    void handle(T argument);

}