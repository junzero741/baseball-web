const makeDecimalPoint = (value, fix) => {
	let result = (value + "")
		.split("")
		.slice(0, fix + 2)
		.join("");
	if (result === "0" || result === "1") result += ".0";
	while (result.length !== fix + 2) result += "0";
	return result;
};
export default makeDecimalPoint;
