(function(window) {
window.env = window.env || {};
window.env.environment = 'dev';
window.env.PRODUCT_SERVER_URL = 'http://localhost:5000/v1';
window.env.IDP_URL = '';
window.env.auth = {
    ClientId: '',
    UserPoolId: ''
}
}(this));