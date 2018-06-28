package br.com.rubyit.resseler.core.commons.dto;

import br.com.rubyit.resseler.commons.kernel.core.Identification;

/**
 * Class UserDTO
 * @author b11527
 *
 */
public class UserDTO extends Identification {

    private Long profileId;
    private Long partnerId;
    private String userCod;
    private String userPwd;
    private String userAct;
    private String userWord;
    private String userGrp;

    /**
     * Constructor default
     */
    public UserDTO() {
        super();
    }

    /**
     * the profileId
     * @return the profileId
     */
    public Long getProfileId() {
        return profileId;
    }

    /**
     * the profileId to set
     * @param profileId 
     */
    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    /**
     * the partnerId
     * @return the partnerId
     */
    public Long getPartnerId() {
        return partnerId;
    }

    /**
     * the partnerId to set
     * @param partnerId 
     */
    public void setPartnerId(Long partnerId) {
        this.partnerId = partnerId;
    }

    /**
     * the userCod
     * @return the userCod
     */
    public String getUserCod() {
        return userCod;
    }

    /**
     * the userCod to set
     * @param userCod 
     */
    public void setUserCod(String userCod) {
        this.userCod = userCod;
    }

    /**
     * the userPwd
     * @return the userPwd
     */
    public String getUserPwd() {
        return userPwd;
    }

    /**
     * the userPwd to set
     * @param userPwd 
     */
    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    /**
     * the userAct
     * @return the userAct
     */
    public String getUserAct() {
        return userAct;
    }

    /**
     * the userAct to set
     * @param userAct 
     */
    public void setUserAct(String userAct) {
        this.userAct = userAct;
    }

    /**
     * the userWord
     * @return the userWord
     */
    public String getUserWord() {
        return userWord;
    }

    /**
     * the userWord to set
     * @param userWord 
     */
    public void setUserWord(String userWord) {
        this.userWord = userWord;
    }

    /**
     * the userGrp
     * @return the userGrp
     */
    public String getUserGrp() {
        return userGrp;
    }

    /**
     * the userGrp to set
     * @param userGrp 
     */
    public void setUserGrp(String userGrp) {
        this.userGrp = userGrp;
    }

}
