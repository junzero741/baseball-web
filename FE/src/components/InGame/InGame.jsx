import { useContext, useEffect, useState } from "react";
import styled from "styled-components";
import ScoreBoard from "./ScoreBoard/ScoreBoard";
import LineUp from "./LineUp/LineUp";
import Ground from "./Ground/Ground";
import Record from "./Record/Record";
import { MainContext } from "../Main";
import useFetch from "../../utils/useFetch/useFetch";

const InGame = () => {
	const { gameId, teamId, loginStatus } = useContext(MainContext);
	const url = `https://baseball-ahpuh.herokuapp.com/games/${gameId}?teamId=${teamId}`;
	const { data, fetchData } = useFetch({ url: url, initialValue: null });

	const [slideScoreBoard, toggleScoreBoard] = useState(false);
	const [slideLineUp, toggleLineUp] = useState(false);
	const [isDark, setDark] = useState(false);
	const clickMain = () => {
		toggleScoreBoard(false);
		toggleLineUp(false);
		setDark(false);
	};
	return loginStatus && data ? (
		<StyledInGame>
			<ScoreBoard slide={slideScoreBoard} toggle={toggleScoreBoard} isDark={isDark} setDark={setDark} gameId={gameId} />
			<LineUp slide={slideLineUp} toggle={toggleLineUp} isDark={isDark} setDark={setDark} gameId={gameId} />
			<Main onClick={clickMain} isDark={isDark}>
				<Ground reloadData={fetchData} data={data} teamId={teamId} url={url} />
				<Record data={data} />
			</Main>
		</StyledInGame>
	) : (
		<WrongApproach>올바르지 않은 접근입니다</WrongApproach>
	);
};

export default InGame;

const StyledInGame = styled.div`
	position: relative;
	overflow: hidden;
	margin-top: 30px;
`;
const Main = styled.div`
	height: 720px;
	filter: ${({ isDark }) => (isDark ? "brightness(20%)" : "")};
	transition: filter 400ms;

	font-size: 5rem;
	color: #fff;
`;
const WrongApproach = styled.div`
	height: 720px;
	display: flex;
	align-items: center;
	justify-content: center;
	font-size: 30px;
	color: #fff;
`;
