import java.util.*;

/**
 * Created by Cube27 on 19.07.2017.
 */
public class Search implements ISearcher {

    HashMap<String,Long> mapNamesAndDates = new HashMap<String,Long>();

    public Search(){
        //default constructor
    }

    @Override
    public void refresh(String[] classNames, long[] modificationDates) {
        // Если я правильно понял то classNames[0]==modificationDates[0]
        if(classNames.length!=modificationDates.length){
            return;
        }

        for(int i=0;i<classNames.length;i++){
            //добавляем в мапу имя -> дата
            mapNamesAndDates.put(classNames[i],modificationDates[i]);
        }
    }

    @Override
    public String[] guess(String start) {
        // Эти два массива буду использовать для выборки сортировки
        ArrayList<String> classNames = new ArrayList(12);
        ArrayList<Long> modifDates = new ArrayList(12);
        // массив который будем возвращать
        String[] first12Elements = new String[12];

        int k =0;
        for(Map.Entry<String, Long> entry: mapNamesAndDates.entrySet()){
            String keyString = entry.getKey();
            Long modifDate = entry.getValue();
            //ключ начинается со start
            if(keyString.startsWith(start)){
                classNames.add(keyString);//добавляем имя класса в массив
                modifDates.add(modifDate);//добавляем дату изменения в массив
                k++;
            };
            if(k==12){
                break;
            }
        }

        for(int i=1;i<modifDates.size();i++){
            //если дата меньше предыдущей или если они равны, но compareTo имен классов < 0
            if(modifDates.get(i)>modifDates.get(i-1) ||
                    (modifDates.get(i).longValue() == modifDates.get(i-1).longValue() &&
                            classNames.get(i).compareTo(classNames.get(i-1))<0)){

                //сохраняем удаляемые значения
                Long saveDate = modifDates.get(i-1);
                String saveClassName = classNames.get(i-1);
                //удаляем выбранное
                modifDates.remove(i-1);
                classNames.remove(i-1);

                //добавляем их вперед
                modifDates.add(i,saveDate);
                classNames.add(i,saveClassName);
                //начинаем итерацию с нуля
                i=0;
            }

        }
        return first12Elements = classNames.toArray(new String[classNames.size()]);
    }

}
