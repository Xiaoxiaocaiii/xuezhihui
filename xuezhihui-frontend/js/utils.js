/* 学知汇 · 工具函数 */
/* ---------- 用户信息 ---------- */
function setUserInfo(user) {
    localStorage.setItem('userInfo', JSON.stringify(user));
}
function getUserInfo() {
    var raw = localStorage.getItem('userInfo');
    if (!raw) return { id: 0, username: '同学' };
    try { return JSON.parse(raw); } catch (e) { return { id: 0, username: '同学' }; }
}
function getUsername() { return getUserInfo().username; }
function getUserId()   { return getUserInfo().id; }

/* ---------- Toast ---------- */
function showToast(message, type) {
    type = type || 'info';
    var container = document.getElementById('toast-container');
    if (!container) {
        container = document.createElement('div');
        container.id = 'toast-container';
        container.className = 'toast-container';
        document.body.appendChild(container);
    }
    var icons = { success: '✅', error: '❌', warning: '⚠️', info: 'ℹ️' };
    var toast = document.createElement('div');
    toast.className = 'toast toast-' + type;
    toast.innerHTML = '<span class="toast-icon">' + (icons[type] || 'ℹ️') + '</span>' +
                      '<span class="toast-message">' + message + '</span>';
    container.appendChild(toast);
    setTimeout(function () {
        if (toast.parentNode) toast.parentNode.removeChild(toast);
    }, 3000);
}

/* ---------- DOM 快捷操作 ---------- */
function $(id) { return document.getElementById(id); }

/* ---------- 通用事件绑定 ---------- */
function bindEnterSubmit(formId) {
    var form = $(formId);
    if (!form) return;
    form.addEventListener('keydown', function (e) {
        if (e.key === 'Enter') { e.preventDefault(); form.dispatchEvent(new Event('submit')); }
    });
}
