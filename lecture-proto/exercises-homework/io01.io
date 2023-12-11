"Hello world!" println

("Hello" .. " " .. "all!") println

helloMsg := "Hello universe!"

updateSlot("helloMsg", "Hello universe v2!")

helloMsg println

printHello := method(name,
	"Hello #{name}!" interpolate println
)

printHello("User")