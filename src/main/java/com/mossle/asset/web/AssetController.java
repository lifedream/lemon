package com.mossle.asset.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mossle.api.user.UserConnector;

import com.mossle.asset.persistence.domain.AssetInfo;
import com.mossle.asset.persistence.domain.AssetLend;
import com.mossle.asset.persistence.manager.AssetInfoManager;
import com.mossle.asset.persistence.manager.AssetLendManager;

import com.mossle.core.export.Exportor;
import com.mossle.core.export.TableModel;
import com.mossle.core.mapper.BeanMapper;
import com.mossle.core.page.Page;
import com.mossle.core.query.PropertyFilter;
import com.mossle.core.spring.MessageHelper;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("asset")
public class AssetController {
    private AssetLendManager assetLendManager;
    private AssetInfoManager assetInfoManager;
    private Exportor exportor;
    private BeanMapper beanMapper = new BeanMapper();
    private UserConnector userConnector;
    private MessageHelper messageHelper;

    @RequestMapping("index")
    public String index(@ModelAttribute Page page,
            @RequestParam Map<String, Object> parameterMap, Model model) {
        List<PropertyFilter> propertyFilters = PropertyFilter
                .buildFromMap(parameterMap);
        page = assetLendManager.pagedQuery(page, propertyFilters);

        model.addAttribute("page", page);

        return "asset/index";
    }

    // ~ ======================================================================
    @Resource
    public void setAssetLendManager(AssetLendManager assetLendManager) {
        this.assetLendManager = assetLendManager;
    }

    @Resource
    public void setAssetInfoManager(AssetInfoManager assetInfoManager) {
        this.assetInfoManager = assetInfoManager;
    }

    @Resource
    public void setExportor(Exportor exportor) {
        this.exportor = exportor;
    }

    @Resource
    public void setUserConnector(UserConnector userConnector) {
        this.userConnector = userConnector;
    }

    @Resource
    public void setMessageHelper(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }
}
