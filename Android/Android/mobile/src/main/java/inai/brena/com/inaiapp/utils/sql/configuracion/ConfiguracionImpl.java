package inai.brena.com.inaiapp.utils.sql.configuracion;

import java.util.List;

/**
 * Created by DanielBrena on 29/10/15.
 */
public interface ConfiguracionImpl {
    public int insert(Configuracion configuracion);
    public int delete(String id);
    public int deleteAll();
    public int update(Configuracion configuracion);
    public List<Configuracion> selectAll();
    public Configuracion selectById(String id);
    public Configuracion selectByName(String nombre);
}
