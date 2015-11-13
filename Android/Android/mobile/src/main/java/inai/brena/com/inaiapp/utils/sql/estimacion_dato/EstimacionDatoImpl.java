package inai.brena.com.inaiapp.utils.sql.estimacion_dato;

import java.util.List;

/**
 * Created by DanielBrena on 24/10/15.
 */
public interface EstimacionDatoImpl {
    public int insert(EstimacionDato estimacionDato);
    public int insertAll(List<EstimacionDato> estimacionDatoList);
    public int delete(String id);
    public int deleteAll();
    public int update(EstimacionDato estimacionDato);
    public List<EstimacionDato> selectAll();
    public EstimacionDato selectById(String id);
    public List<EstimacionDato>  selectByIdEstimacion(String id);
}
