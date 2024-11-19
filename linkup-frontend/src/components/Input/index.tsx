

interface InputProps{
    nameLabel: string; 
    namePlaceholder: string; 
    setValue: (value: string) => void;
    type: string
}

const Input = ({ nameLabel, namePlaceholder, setValue, type} : InputProps) => {
  return (
    <div>
      <label className="flex flex-col text-[14px] font-medium gap-2 w-full">{nameLabel}
          <input className="flex rounded-md py-3 px-2 border-[#c4c4c4] border-2 w-full"
          type={type} placeholder={namePlaceholder} onChange={(e) => setValue(e.target.value)}/>
      </label>
    </div>
  )
}

export default Input
