package com.netcracker.laboratory.portlets.utils.preferences.renderers.custom;

import com.google.common.collect.Lists;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portlet.documentlibrary.model.DLFolder;
import com.liferay.portlet.documentlibrary.model.DLFolderConstants;
import com.liferay.portlet.documentlibrary.service.DLAppServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFolderLocalServiceUtil;
import com.netcracker.laboratory.portlets.utils.preferences.annotations.PortletPreference;
import com.netcracker.laboratory.portlets.utils.preferences.renderers.list.ListValue;
import com.netcracker.laboratory.portlets.utils.preferences.renderers.list.ListValuesProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

@Component
public class ImagesListValueProvider implements ListValuesProvider {

    @Value("${portal.customer.account.site.id}")
    private BigInteger groupId;

    @Override
    public String getKey() {
        return "folders";
    }

    @Override
    public List<ListValue> getListValues(PortletPreference preferenceSettings) {
        List<ListValue> result = Lists.newArrayList();
        result.add(new ListValue("Home", "0"));
        Long parentFolderId = DLFolderConstants.DEFAULT_PARENT_FOLDER_ID;
        List<DLFolder> dlFolders;
        try {
            dlFolders = DLFolderLocalServiceUtil.getFolders(groupId.longValue(), parentFolderId);
            for (DLFolder dlFolder : dlFolders) {
                Folder folder = DLAppServiceUtil.getFolder(groupId.longValue(), parentFolderId, dlFolder.getName());
                result.add(new ListValue(" - " + folder.getName(), String.valueOf(folder.getFolderId())));
            }
        } catch (SystemException | PortalException e) {
            e.printStackTrace();
        }
        return result;
    }
}
