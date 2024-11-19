import { useState } from "react";
import { api } from "../../lib/axios";
import Input from "../../components/Input";
import Button from "../../components/Button";
import { useNavigate } from "react-router-dom";
import logo from "../../assets/svg/logo-main.svg";

const Login = () => {
  const [user, setUser] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  async function signIn() {
    await api
      .post("/api/auth", {
        user,
        password,
      })
      .then((json) => {
        console.log(json.data);
      });
  }

  function submitLogin() {
    signIn();
  }

  function notHaveAccount() {
    navigate("/register");
  }

  return (
    <div className="w-full h-screen flex justify-between items-center bg-[#F8F8F8]">
      <div className="w-1/2 h-full flex justify-center items-center">
        <div className="gap-10 h-full w-80 flex flex-col justify-center">
          <div>
            <h1 className="text-black text-[36px] font-medium">SIGNIN</h1>
            <p className="text-[#636364]">
              Bem-Vindo de volta! Por favor preencha seus detalhes.
            </p>
          </div>
          <div className="flex flex-col gap-10">
            <Input
              nameLabel="User"
              namePlaceholder="Digite seu user ou username"
              setValue={setUser}
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
              text="Não tenho conta"
              type="cancel"
              fun={notHaveAccount}
            ></Button>
          </div>
        </div>
      </div>
      <div className="h-full w-1/2 flex justify-center bg-black">
        <img src={logo} alt="" className=" h-full object-cover w-full object-top" />
      </div>
    </div>
  );
};

export default Login;
