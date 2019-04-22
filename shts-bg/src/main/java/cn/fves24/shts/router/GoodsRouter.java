package cn.fves24.shts.router;

import cn.fves24.shts.common.ComMsg;
import cn.fves24.shts.common.RespVO;
import cn.fves24.shts.model.Goods;
import cn.fves24.shts.model.User;
import cn.fves24.shts.mysql.mapper.GoodsMapper;
import cn.fves24.shts.validation.Validation;
import cn.fves24.shts.validation.ValidationResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class GoodsRouter {
    private GoodsMapper goodsMapper;
    private HttpServletRequest request;

    public GoodsRouter(GoodsMapper goodsMapper, HttpServletRequest request) {
        this.goodsMapper = goodsMapper;
        this.request = request;
    }

    @RequestMapping("/goods/save")
    public RespVO saveGoods(Goods goods) {
        ValidationResult result = Validation.validateGoods(goods);
        if (result.isHasErrors()) {
            return RespVO.getFail(result.getErrMsg());
        }
        try {
            Integer save = goodsMapper.saveGoods(getCurrentUser().getId(), goods);
            if (save != null && save == 1) {
                return RespVO.getSuccess("发布商品成功");
            }
        } catch (Exception e) {
            return RespVO.getFail(ComMsg.getFail("发布商品失败"));
        }
        return RespVO.getFail(ComMsg.getFail("发布商品失败"));
    }

    @PostMapping("/goods/update")
    public RespVO updateStatus(Integer gid, Integer status) {
        if (gid == null || !(
                status == Goods.INSALE ||
                        status == Goods.INTRANSACTION ||
                        status == Goods.SOLD)) {
            return RespVO.getFail(ComMsg.PARAMETER_ERROR);
        }
        Integer integer = goodsMapper.updateGoodsStatus(getCurrentUser().getId(), gid, status);
        if (integer != null && integer == 1) {
            return RespVO.getSuccess(ComMsg.getSuccess("修改状态成功"));
        }
        return RespVO.getFail(ComMsg.getFail("修改状态失败"));

    }

    @PostMapping("/goods/gid")
    public RespVO queryGoodsById(Integer gid) {
        if (gid == null) {
            return RespVO.getFail(ComMsg.PARAMETER_ERROR);
        }
        Goods goods = goodsMapper.selectGoodsById(gid);
        return RespVO.getSuccess(goods);
    }

    @PostMapping("/goods/uid")
    public RespVO queryGoodsByUserId(Integer uid) {
        if (uid == null) {
            return RespVO.getFail(ComMsg.PARAMETER_ERROR);
        }
        List<Goods> goods = goodsMapper.selectGoodsByUid(uid);
        return RespVO.getSuccess(goods);
    }

    @PostMapping("/goods")
    public RespVO queryGoods() {
        List<Goods> goods = goodsMapper.selectGoods();
        return RespVO.getSuccess(goods);
    }

    private User getCurrentUser() {
        HttpSession session = request.getSession();
        return (User) session.getAttribute("user");
    }
}
