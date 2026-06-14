/* 学知汇 · API 封装 */
(function () {
  'use strict';
  var BASE_URL = 'http://localhost:8080';

  async function request(method, url, data, isFormData) {
    var headers = {};
    if (!isFormData) headers['Content-Type'] = 'application/json';
    var body = data ? (isFormData ? data : JSON.stringify(data)) : null;
    var response = await fetch(BASE_URL + url, { method: method, headers: headers, body: body });
    var result = await response.json();
    if (result.code !== 200) throw new Error(result.message || '操作失败');
    return result;
  }

  /* ---------- 认证 ---------- */
  window.login = async function (username, password) {
    var result = await request('POST', '/api/auth/login', { username: username, password: password });
    if (result.data && result.data.id) setUserInfo(result.data);
    return result;
  };

  window.register = async function (username, password) {
    return await request('POST', '/api/user/register', { username: username, password: password });
  };

  /* ---------- 资源 ---------- */
  window.getResources = async function () {
    return await request('GET', '/api/resources');
  };

  window.uploadResource = async function (file, title) {
    var formData = new FormData();
    formData.append('file', file);
    formData.append('title', title);
    formData.append('userId', getUserId());
    formData.append('username', getUsername());
    return await request('POST', '/api/resources/upload', formData, true);
  };

  window.deleteResource = async function (id) {
    return await request('DELETE', '/api/resources/' + id, { userId: getUserId() });
  };

  window.downloadResourceToLocal = async function (id) {
    var response = await fetch(BASE_URL + '/api/resources/' + id + '/download');
    var disposition = response.headers.get('Content-Disposition');
    var fileName = 'download';
    if (disposition) {
      var starMatch = disposition.match(/filename\*=UTF-8''([^;\n]*)/i);
      if (starMatch && starMatch[1]) {
        fileName = decodeURIComponent(starMatch[1].trim());
      } else {
        var match = disposition.match(/filename[^;=\n]*=((['"]).*?\2|[^;\n]*)/);
        if (match && match[1]) fileName = match[1].replace(/['"]/g, '');
      }
    }
    var blob = await response.blob();
    var url = window.URL.createObjectURL(blob);
    var a = document.createElement('a');
    a.href = url;
    a.download = fileName;
    document.body.appendChild(a);
    a.click();
    document.body.removeChild(a);
    window.URL.revokeObjectURL(url);
  };

  /* ---------- 评论 ---------- */
  window.getComments = async function (resourceId) {
    return await request('GET', '/api/resources/' + resourceId + '/comments');
  };

  window.postComment = async function (resourceId, content) {
    return await request('POST', '/api/resources/' + resourceId + '/comments', {
      userId: getUserId(),
      content: content
    });
  };

  window.deleteComment = async function (resourceId, commentId) {
    return await request('DELETE', '/api/resources/' + resourceId + '/comments/' + commentId, { userId: getUserId() });
  };
})();
