<?php

header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: OPTIONS,GET,POST,PUT,DELETE");
header("Access-Control-Max-Age: 3600");
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");


$usr = $_POST['user'];
$pwd = $_POST['pwd'];

if($usr == 'mhs' && $pwd == 'p455word'){
    $data = array();
    $data['fullname'] = 'Arif Laksito';
    $data['email'] = 'arif.laksito@amikom.ac.id';
    $data['dept'] = 'Innovation Center';
    $data['company'] = 'Universitas Amikom';

    echo json_encode($data);

}else{
    header("HTTP/1.1 401 Unauthorized");
    $data['msg'] = 'User dan Password tidak sesuai';
    echo json_encode($data);
}