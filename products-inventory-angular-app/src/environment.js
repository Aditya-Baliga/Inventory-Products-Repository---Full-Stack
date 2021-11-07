(function(window) {
window.env = window.env || {};
window.env.environment = 'dev';
// window.env.PRODUCT_SERVER_URL = 'http://localhost:5000/v1';
//window.env.PRODUCT_SERVER_URL ='http://product-inventory.us-east-1.elasticbeanstalk.com/v1'
window.env.PRODUCT_SERVER_URL ='https://ii1hqokgud.execute-api.us-east-1.amazonaws.com/dev1/v1'
// window.env.IDP_URL = 'https://cognito-idp.{REGION_ID}.amazonaws.com/{POOL_ID}';
window.env.IDP_URL = 'https://cognito-idp.us-east-1.amazonaws.com/us-east-1_Ln5qSp5cb';
window.env.auth = {
    ClientId: '1roo1ojd9h6jvme5doop3aq120',
    UserPoolId: 'us-east-1_Ln5qSp5cb'
}
}(this));