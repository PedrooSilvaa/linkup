import { useState } from "react"
import { api } from "../../lib/axios"
import Input from "../../components/Input"
import Button from "../../components/Button"
import { routes } from "../../routes/routes"
import { useNavigate } from "react-router-dom"


const Login = () => {

    const [user, setUser] = useState("")
    const [password, setPassword] = useState("")
    const navigate = useNavigate();

    async function signIn() {
        await api.post("/api/auth", {
            user, password
        })
        .then((json) => {
            console.log(json.data);
        })
    }

    function submitLogin(){
        signIn();
    }

    function notHaveAccount(){
        navigate("/register");
    }

  return (
    <div className="w-full h-screen flex justify-center items-center flex-col bg-slate-400">
      <Input nameLabel="User" namePlaceholder="Digite seu User ou Username" setValue={setUser} type="text"></Input>
      <Input nameLabel="Password" namePlaceholder="Digite sua senha" setValue={setPassword} type="text"></Input>
      <div>
        <Button text="Confirmar" type="submit" fun={submitLogin}></Button>
        <Button text="Não tenho conta" type="cancel" fun={notHaveAccount}></Button>
      </div>
    </div>
  )
}

export default Login
