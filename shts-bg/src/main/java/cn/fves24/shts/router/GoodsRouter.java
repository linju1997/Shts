package cn.fves24.shts.router;

import cn.fves24.shts.common.ComMsg;
import cn.fves24.shts.common.RespVO;
import cn.fves24.shts.dto.GoodsInfo;
import cn.fves24.shts.entity.Goods;
import cn.fves24.shts.entity.User;
import cn.fves24.shts.mysql.mapper.GoodsMapper;
import cn.fves24.shts.mysql.mapper.UserMapper;
import cn.fves24.shts.validation.Validation;
import cn.fves24.shts.validation.ValidationResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/goods")
public class GoodsRouter {
    private GoodsMapper goodsMapper;
    private UserMapper userMapper;
    private HttpServletRequest request;

    public GoodsRouter(GoodsMapper goodsMapper, UserMapper userMapper, HttpServletRequest request) {
        this.goodsMapper = goodsMapper;
        this.userMapper = userMapper;
        this.request = request;
    }

    @PostMapping("/publish")
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
            System.out.println(e.getMessage());
            return RespVO.getFail(ComMsg.getFail("发布商品失败"));
        }
        return RespVO.getFail(ComMsg.getFail("发布商品失败"));
    }

    /**
     * 获取轮播的三条商品数据
     *
     * @return 返回结果
     */
    @PostMapping("/swipe")
    public RespVO swipeGoods() {
        List<Goods> goodsList = goodsMapper.selectGoodsLast4();
        return RespVO.getSuccess(goodsList);
    }

    /**
     * 获取最新发布的10条商品数据
     *
     * @return 返回结果
     */
    @PostMapping("/new")
    public RespVO newPublishGoods() {
        List<Goods> goods = goodsMapper.selectGoodsTop10();
        return RespVO.getSuccess(goods);
    }


    /**
     * 更新商品数据
     *
     * @param gid    商品ID
     * @param status 出售状态
     * @return 返回结果
     */
    @PostMapping("/update")
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

    /**
     * 根据商品ID查询商品数据
     *
     * @param gid 商品ID
     * @return 返回结果
     */
    @PostMapping("/gid")
    public RespVO queryGoodsById(Integer gid) {
        log.info("查询商品，id:{}", gid);
        if (gid == null) {
            log.info("id eq null");
            return RespVO.getFail(ComMsg.PARAMETER_ERROR);
        }
        Goods goods = goodsMapper.selectGoodsById(gid);
        log.info("id{},{}", gid, goods);
        return RespVO.getSuccess(goods);
    }

    @PostMapping("/uid")
    public RespVO queryGoodsByUserId(Integer uid) {
        if (uid == null) {
            return RespVO.getFail(ComMsg.PARAMETER_ERROR);
        }
        List<Goods> goods = goodsMapper.selectGoodsByUid(uid);
        return RespVO.getSuccess(goods);
    }

    @PostMapping("/all")
    public RespVO queryGoods() {
        List<Goods> goods = goodsMapper.selectGoods();
        return RespVO.getSuccess(goods);
    }

    private User getCurrentUser() {
        HttpSession session = request.getSession();
        return (User) session.getAttribute("user");
    }
}
