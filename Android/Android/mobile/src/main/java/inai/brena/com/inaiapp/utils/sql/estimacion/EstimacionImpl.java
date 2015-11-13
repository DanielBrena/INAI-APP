package inai.brena.com.inaiapp.utils.sql.estimacion;

import java.util.List;

/**
 * Created by DanielBrena on 24/10/15.
 */
public interface EstimacionImpl {
    public int insert(Estimacion estimacion);
    public int delete(String id);
    public int deleteAll();
    public int update(Estimacion estimacion);
    public List<Estimacion> selectAll();
    public Estimacion selectById(String id);
    public List<Estimacion> selectAllByPlantilla(String tipo);
}
