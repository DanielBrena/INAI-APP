package inai.brena.com.inaiapp.utils.sql.pregunta;

import java.util.List;

/**
 * Created by DanielBrena on 25/10/15.
 */
public interface PreguntaImpl {
    public int insert(Pregunta pregunta);
    public int delete(String id);
    public int deleteAll();
    public int update(Pregunta pregunta);
    public List<Pregunta> selectAll();
    public Pregunta selectById(String id);
}
