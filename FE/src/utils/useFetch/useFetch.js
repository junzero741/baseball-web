import { useState, useEffect } from "react";

const useFetch = ({ url, initialValue }) => {
	const [data, setData] = useState(initialValue);

	useEffect(() => {
		fetchData();
	}, []);

	const fetchData = async (method, body) => {
		const header = {
			method: method,
			headers: { "Content-Type": "application/json" },
			body: JSON.stringify(body),
		};

		try {
			const response = method ? await fetch(url, header) : await fetch(url);
			const json = await response.json();
			setData(() => json);
		} catch (error) {
			console.error(`fetch failed due to : ${error}`);
		}
	};

	return { data, fetchData };
};

export default useFetch;
