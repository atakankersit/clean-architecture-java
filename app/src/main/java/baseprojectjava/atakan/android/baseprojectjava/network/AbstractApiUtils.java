package baseprojectjava.atakan.android.baseprojectjava.network;


import baseprojectjava.atakan.android.baseprojectjava.manager.TokenManager;

public abstract class AbstractApiUtils implements ApiUtilsInterface {

    protected UnauthorizedAPIService unauthorizedAPIService;
    protected AuthorizedAPIService authorizedAPIService;
    protected CustomCallbackSender customCallbackSender;
    protected TokenManager tokenManager;


    public AbstractApiUtils(UnauthorizedAPIService unauthorizedAPIService, AuthorizedAPIService authorizedAPIService, CustomCallbackSender customCallbackSender, TokenManager tokenManager) {
        this.unauthorizedAPIService = unauthorizedAPIService;
        this.authorizedAPIService = authorizedAPIService;
        this.customCallbackSender = customCallbackSender;
        this.tokenManager = tokenManager;
    }
}
