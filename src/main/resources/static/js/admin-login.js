$(function () {
    // フォームが送信される際のイベントリスナーを設定
    $('#loginForm').on('submit', function (event) {

        // デフォルトのフォーム送信動作を防止
        event.preventDefault();

        // 入力された管理者IDとパスワードを取得
        const adminId = $('#idBox').val();
        const password = $('#passwordBox').val();

        //管理者IDまたはパスワードが空の場合、エラーメッセージを表示
        if (isAnyEmptyOrNull(adminId, password)) {
            $('#errorMessage').css('visibility', 'unset'); // エラーメッセージを表示
            $('#errorMessage').html(EMPTY_ERROR_MESSAGE); // エラーメッセージを設定
            return; // 処理を中断
        }

        // 管理者IDまたはパスワードが16文字を超える場合、エラーメッセージを表示
        if (adminId.length > MAX_LENGTH_ADMIN_ID || password.length > MAX_LENGTH_ADMIN_PASS) {
            $('#errorMessage').css('visibility', 'unset'); // エラーメッセージを表示
            $('#errorMessage').html(OVER_LENGTH_ERROR_MESSAGE); // エラーメッセージを設定
            return; // 処理を中断
        }

        // 管理者IDまたはパスワードに半角英数字以外の文字が含まれている場合、エラーメッセージを表示
        if (!isAllAlphanumeric(adminId, password)) {
            $('#errorMessage').css('visibility', 'unset'); // エラーメッセージを表示
            $('#errorMessage').html(ILLEGAL_CHARACTER_ERROR_MESSAGE); // エラーメッセージを設定
            return; // 処理を中断
        }

        // すべてのバリデーションを通過した場合、フォームを送信
        this.submit();
    });
});