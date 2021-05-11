import { useState, useEffect } from "react";

const useFetch = ({ url, initialValue }) => {
	const [data, setData] = useState(initialValue);

	useEffect(() => {
		fetchData(url);
	}, []);

	const fetchData = async (url) => {
		try {
			const response = await fetch(url);
			const json = await response.json();
			setData(() => json);
		} catch (error) {
			console.error(`fetch failed due to : ${error}`);
		}
	};

	return { data, fetchData };
};

export default useFetch;
