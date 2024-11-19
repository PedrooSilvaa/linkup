
interface ButtonProps{
    type: string;
    text: string;
    fun: () => void;
}

const Button = ({ type, text, fun } : ButtonProps) => {
  return (
    <div >
        {type == "submit" ? (
                <button className='bg-[#EA454C] text-white py-3 rounded-xl w-full font-medium' onClick={() => fun()}>{text}</button>
            ) 
            :
            (
                <button className='text-[#EA454C] bg-white py-3 rounded-xl w-full border-2 border-[#c4c4c4] font-medium' onClick={() => fun()}>{text}</button>
            )
        }
    </div>
  )
}

export default Button
