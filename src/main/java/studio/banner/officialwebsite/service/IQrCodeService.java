package studio.banner.officialwebsite.service;

import studio.banner.officialwebsite.entity.QrCode;

import java.util.List;

/**
 * @author hyy
 * @date 2021/5/20 16:45
 * @role
 */
public interface IQrCodeService {
    /**
     * 添加二维码图片
     * @param qrCode
     * @return boolean
     */
    boolean insert(QrCode qrCode);

    /**
     * 删除二维码图片
     * @param key
     * @return boolean
     */
    boolean delete(String key);

    /**
     * 根据二维码id删除二维码信息
     * @param qrCodeId
     * @return
     */
    boolean deleteById(Integer qrCodeId);

    /**
     * 根据二维码id更改二维码信息
     * @param qrCode
     * @return
     */
    boolean update(QrCode qrCode);

    /**
     * 根据照片名称查询照片
     * @param key
     * @return String
     */
    QrCode select(String key);

    /**
     * 查询所有图片
     * @return
     */
    List<QrCode> selectAll();

    /**
     * 根据二维码id信息查询二维码
     * @param qrCodeId
     * @return
     */
    QrCode selectById(Integer qrCodeId);

}
