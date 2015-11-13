package inai.brena.com.inaiapp.utils.sql.categoria_principios;

import java.util.List;

/**
 * Created by DanielBrena on 25/10/15.
 */
public interface CategoriaPrincipiosImpl {
    public int insert(CategoriaPrincipios categoriaPrincipios);
    public int delete(String id);
    public int deleteAll();
    public int update(CategoriaPrincipios categoriaPrincipios);
    public List<CategoriaPrincipios> selectAll();
    public CategoriaPrincipios selectById(String id);
}
