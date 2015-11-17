package inai.brena.com.inaiapp.utils.sql.tip;

import java.util.List;

/**
 * Created by DanielBrena on 16/11/15.
 */
public interface TipImpl {
    public Tip selectById(String id);
    public List<Tip> selectAll();
    public int insert(Tip tip);
}
