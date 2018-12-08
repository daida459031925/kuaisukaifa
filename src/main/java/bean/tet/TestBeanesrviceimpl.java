package bean.tet;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;




@Service
@Transactional(readOnly = true)//开启事务//只读模式
public class TestBeanesrviceimpl implements TestBeanservice{


    @Autowired
    private TestBeanDao testBeanDao;

    public List<testBean> findAll(){
        List<testBean> s=testBeanDao.findAll();
        return s;
    }

    public Page<testBean> findByPage(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return testBeanDao.findByPage();
    }

    public int add(){
        int s=testBeanDao.save();
        return s;
    }
}
