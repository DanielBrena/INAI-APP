package inai.brena.com.inaiapp.utils.sql.dato;

import java.util.List;

/**
 * Created by DanielBrena on 24/10/15.
 */
public interface DatoImpl {
    public int insert(Dato dato);
    public int delete(String id);
    public int deleteAll();
    public int update(Dato dato);
    public List<Dato> selectAll();
    public Dato selectById(String id);
}
