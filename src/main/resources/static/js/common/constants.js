/** 管理者ログインのエラーメッセージ */
// 管理者IDとパスワードのどちらかが空白
const EMPTY_ERROR_MESSAGE = '入力必須項目です';

// 管理者IDとパスワードのどちらかが16文字以上
const OVER_LENGTH_ERROR_MESSAGE = 'ID、パスワードは16文字以下です';

// 管理者IDとパスワードのどちらかに半角英数字以外の文字が使われている
const ILLEGAL_CHARACTER_ERROR_MESSAGE = 'IDとパスワードに用いられるのは半角英数字のみです';