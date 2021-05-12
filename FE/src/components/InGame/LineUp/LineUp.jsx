import styled from "styled-components";
import Table from "./Table";
import useFetch from "../../../utils/useFetch/useFetch";

const LineUp = ({ gameId, slide, toggle, isDark, setDark }) => {
	const { data, fetchData } = useFetch({ url: `https://baseball-ahpuh.herokuapp.com/games/${gameId}/lineup`, initialValue: null });

	const slideLineUp = async () => {
		if (isDark) return;
		await fetchData()
		toggle(true);
		setDark(true);
	};
	return (
		<StyledLineUp>
			<NearChecker onMouseEnter={slideLineUp} />
			<Table slide={slide} data={data} />
		</StyledLineUp>
	);
};
const StyledLineUp = styled.div``;
const NearChecker = styled.div`
	position: absolute;
	width: 1280px;
	height: 40px;
	top: 680px;
`;

export default LineUp;
