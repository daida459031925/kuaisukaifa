package bean.tet;

import com.github.pagehelper.Page;

import java.util.List;

public interface TestBeanservice {

    public List<testBean> findAll();

    public Page<testBean> findByPage(int pageNo, int pageSize);
}
