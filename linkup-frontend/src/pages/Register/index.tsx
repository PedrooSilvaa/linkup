import { useState } from "react";
import { api } from "../../lib/axios";
import Input from "../../components/Input";
import Button from "../../components/Button";
import { useNavigate } from "react-router-dom";
import logo from "../../assets/svg/logo-main.svg";

const Register = () => {
  const [user, setUser] = useState("");
  const [password, setPassword] = useState("");
  const [birthDate, setBirthDate] = useState("");
  const [phone, setPhone] = useState("");
  const [username, setUsername] = useState("");
  const navigate = useNavigate();

  async function signUp() {
    await api
      .post("/api/users", {
        user,
        password,
        birthDate,
        phone,
        username,
      })
      .then((json) => {
        console.log(json.data);
        navigate('/')
      });
  }

  function submitLogin() {
    signUp();
  }

  function haveAccount() {
    navigate("/");
  }
  return (
    <div className="w-full h-screen flex justify-between items-center bg-[#F8F8F8]">
      <div className="w-1/2 h-full flex justify-center items-center">
        <div className="gap-10 h-full w-80 flex flex-col justify-center">
          <div>
            <h1 className="text-black text-[36px] font-medium">SIGNUP</h1>
          </div>
          <div className="flex flex-col gap-5">
            <Input
              nameLabel="User"
              namePlaceholder="Digite seu user ou username"
              setValue={setUser}
              type="text"
            ></Input>
            <div className="flex gap-3">
              <Input
                nameLabel="Birth Date"
                namePlaceholder="Digite sua data de nascimento"
                setValue={setBirthDate}
                type="date"
              ></Input>
              <Input
                nameLabel="Phone"
                namePlaceholder="Digite seu telefone"
                setValue={setPhone}
                type="text"
              ></Input>
            </div>
            <div className="flex flex-col gap-6">
              <Input
                nameLabel="Username"
                namePlaceholder="Digite seu user ou username"
                setValue={setUsername}
                type="text"
              ></Input>
              <Input
                nameLabel="Password"
                namePlaceholder="Digite sua senha"
                setValue={setPassword}
                type="text"
              ></Input>
            </div>
            <div className="flex flex-col gap-10">
              <Button text="Confirmar" type="submit" fun={submitLogin}></Button>
              <Button
                text="Tenho conta"
                type="cancel"
                fun={haveAccount}
              ></Button>
            </div>
          </div>
        </div>
      </div>
      <div className="h-full w-1/2 flex justify-center bg-black">
        <img
          src={logo}
          alt=""
          className=" h-full object-cover w-full object-top"
        />
      </div>
    </div>
  );
};

export default Register;
