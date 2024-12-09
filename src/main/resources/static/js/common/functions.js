// 引数の文字列のうちどれか一つでも空文字、nullだった場合にtrueを返す
function isAnyEmptyOrNull(...strs) {
    return strs.some(str => str === "" || str === null);
}

// 引数の文字列全てが半角英数のときtrueを返す
function isAllAlphanumeric(...strs) {
    return strs.every(str => alphanumericRegex.test(str));
}