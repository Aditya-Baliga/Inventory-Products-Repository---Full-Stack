(function(window) {
window.env = window.env || {};
window.env.environment = 'dev';
window.env.PRODUCT_SERVER_URL = 'http://localhost:5000/v1';
window.env.IDP_URL = '12343';
window.env.auth = {
    ClientId: '123',
    UserPoolId: '456'
}
}(this));