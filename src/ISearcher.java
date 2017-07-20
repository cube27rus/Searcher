/**
 * Created by Cube27 on 19.07.2017.
 */
public interface ISearcher {
    /**
     *
     Обновляет внутренние структуры данных для последующего быстро
     го поиска
     *
     @param classNames названия классов в проекте
     *
     @param  modificationDates  дата  модификации  класса  в  формате
     мс, прошедших с 1 января 1970 года
     */
    public void refresh(String[] classNames, long[] modificationDates);
    /**
     *
     Ищет подходящие имена клас
     сов
     *
     Название должно начинаться с start
     *
     @param start начало имени класса
     *
     @return  массив  длины  от  0  до  12,  имена  классов,  упорядоченный  по
     дате модификации и лексиграфически.
     */
    public String[] guess(String start);
}
