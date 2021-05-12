import { useEffect, useState } from "react";
import styled from "styled-components";
import useFetch from "../../utils/useFetch/useFetch";
import Game from "./Game";

const TeamSelect = () => {
	const [isHover, setHover] = useState(false);
	const [alertMessage, setAlertMessage] = useState("참가할 게임을 선택해주세요");
	const { data } = useFetch({ url: "https://baseball-ahpuh.herokuapp.com/games", initialValue: [] });
	const somethingAwesomeCheckTeamAvailable = () => false; // 해당 팀 선택시 게임 가능한 팀인지 체크
	useEffect(() => {
		if (somethingAwesomeCheckTeamAvailable(data)) setAlertMessage(() => "고를수 없는 팀입니다.");
	}, [data]);

	return (
		<StyledTeamSelect>
			<Alert>{alertMessage}</Alert>
			<GameList isHover={isHover} onMouseEnter={() => setHover(true)} onMouseLeave={() => setHover(false)}>
				{data ? data.map((el, i) => <Game {...el} key={i} />) : "loading..."}
			</GameList>
		</StyledTeamSelect>
	);
};

const StyledTeamSelect = styled.div`
	height: 500px;
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: space-evenly;
`;
const Alert = styled.div`
	color: #fff;
`;
const GameList = styled.ul`
	position: relative;
	left: 15px;
	height: 386px;
	width: 745px;
	overflow-y: scroll;
	::-webkit-scrollbar {
		background-color: #a6a7ab;
		width: 13px;
		border-radius: 10px;
		display: ${(props) => (props.isHover ? "" : "none")};
	}
	::-webkit-scrollbar-thumb:vertical {
		background-color: #4c4c4c;
		border-radius: 10px;
		border: 2px solid transparent;
		background-clip: padding-box;
	}
`;

export default TeamSelect;
