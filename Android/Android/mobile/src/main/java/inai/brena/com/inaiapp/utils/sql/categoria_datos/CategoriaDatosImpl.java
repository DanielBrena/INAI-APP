package inai.brena.com.inaiapp.utils.sql.categoria_datos;

import java.util.List;

/**
 * Created by DanielBrena on 23/10/15.
 */
public interface CategoriaDatosImpl {
    public int insert(CategoriaDatos categoriaDatos);
    public int delete(String id);
    public int deleteAll();
    public int update(CategoriaDatos categoriaDatos);
    public List<CategoriaDatos> selectAll();
    public CategoriaDatos selectById(String id);
}
