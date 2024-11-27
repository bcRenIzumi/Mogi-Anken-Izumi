SELECT
    adminID
   ,adminName
   ,password
   ,roleCode
   ,logicDelFlg
   ,createBy
   ,createTime
   ,updateBy
   ,updateTime 
FROM
    MstAdmin
WHERE
    adminID = /*[# mb:p="adminId"]*/ 'A001' /*[/]*/
    AND password = /*[# mb:p="hashedPass"]*/ 'hashed_password_1' /*[/]*/
    AND logicDelFlg = '0'