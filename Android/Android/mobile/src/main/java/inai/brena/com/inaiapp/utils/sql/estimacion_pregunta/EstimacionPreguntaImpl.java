package inai.brena.com.inaiapp.utils.sql.estimacion_pregunta;

import java.util.List;

/**
 * Created by DanielBrena on 25/10/15.
 */
public interface EstimacionPreguntaImpl {
    public int insert(EstimacionPregunta estimacionPregunta);
    public int insertAll(List<EstimacionPregunta> estimacionPreguntaList);
    public int delete(String id);
    public int deleteAll();
    public int update(EstimacionPregunta estimacionPregunta);
    public List<EstimacionPregunta> selectAll();
    public List<EstimacionPregunta>  selectByIdEstimacion(String id);
    public EstimacionPregunta selectById(String id);
}
