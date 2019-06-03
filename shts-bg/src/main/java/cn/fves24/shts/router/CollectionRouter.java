package cn.fves24.shts.router;

import cn.fves24.shts.common.ComMsg;
import cn.fves24.shts.common.RespVO;
import cn.fves24.shts.entity.Collection;
import cn.fves24.shts.entity.User;
import cn.fves24.shts.mysql.mapper.CollectionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author fves
 */
@RestController
public class CollectionRouter {

    private CollectionMapper collectionMapper;
    private HttpServletRequest request;

    @Autowired
    public CollectionRouter(CollectionMapper collectionMapper, HttpServletRequest request) {
        this.collectionMapper = collectionMapper;
        this.request = request;
    }

    @PostMapping("/collection")
    public RespVO collection() {
        List<Collection> collections = collectionMapper.getCollectionsByUserId(getCurrentUser().getId());
        return RespVO.getSuccess(collections);
    }

    @PostMapping("/collection/save")
    public RespVO collection(@SessionAttribute("email") String email, @RequestParam("gid") Integer gid) {
        if (gid == null) {
            return RespVO.getFail(ComMsg.PARAMETER_ERROR);
        }
        try {
            Integer save = collectionMapper.save(email, gid);
            if (save != null && save == 1) {
                return RespVO.getSuccess(ComMsg.COLLECTION_SUCCESS);
            }
        } catch (Exception e) {
            return RespVO.getFail(ComMsg.COLLECTION_FAIL);
        }
        return RespVO.getFail(ComMsg.COLLECTION_FAIL);
    }

    @PostMapping("/collection/cancel")
    public RespVO cancelCollection(Integer gid) {
        if (gid == null) {
            return RespVO.getFail(ComMsg.PARAMETER_ERROR);
        }
        Integer delete = collectionMapper.delete(getCurrentUser().getId(), gid);
        if (delete != null && delete == 1) {
            return RespVO.getSuccess(ComMsg.COLLECTION_CANCEL_SUCCESS);
        }
        return RespVO.getFail(ComMsg.COLLECTION_CANCEL_FAIL);
    }

    @PostMapping("/collection/list")
    public RespVO collections() {
        List<Collection> collections = collectionMapper.getCollections();
        return RespVO.getSuccess(collections);
    }

    private User getCurrentUser() {
        HttpSession session = request.getSession();
        return (User) session.getAttribute("user");
    }
}
